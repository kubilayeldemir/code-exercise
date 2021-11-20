package main

import (
	"encoding/json"
	"fmt"
	"github.com/google/uuid"
	"io/ioutil"
	"net/http"
	"sync"
)

func main() {
	bookHandlers := newBookHandler()
	http.HandleFunc("/books", bookHandlers.books)

	port := ":8080"
	fmt.Println("Started listening on port", port)
	err := http.ListenAndServe(port, nil)
	if err != nil {
		panic("Something wrong")
	}
}

type bookHandlers struct {
	sync.Mutex
	library map[string]Book
}

func (b *bookHandlers) books(w http.ResponseWriter, r *http.Request) {
	switch r.Method {
	case "GET":
		b.get(w, r)
	case "POST":
		b.post(w, r)
	default:
		w.WriteHeader(http.StatusMethodNotAllowed)
	}
}

func (b *bookHandlers) get(w http.ResponseWriter, r *http.Request) {
	books := b.getAllBooks()
	jsonBytes, err := json.Marshal(books)

	if err != nil {
		w.Header().Add("content-type", "application/json")
		w.WriteHeader(http.StatusBadRequest)
		w.Write([]byte(err.Error()))
	}
	w.Header().Add("content-type", "application/json")
	w.WriteHeader(http.StatusOK)
	w.Write(jsonBytes)
}

func (b *bookHandlers) getAllBooks() []Book {
	b.Lock()
	var librarySize = len(b.library)
	var books = make([]Book, librarySize)
	i := 0
	for _, v := range b.library {
		books[i] = v
		i++
	}
	b.Unlock()
	return books
}

func (b *bookHandlers) post(w http.ResponseWriter, r *http.Request) {
	b.Lock()
	bodyBytes, _ := ioutil.ReadAll(r.Body)
	var newBook = Book{}
	err := json.Unmarshal(bodyBytes, &newBook)
	if err != nil {
		w.Header().Add("content-type", "application/json")
		w.WriteHeader(http.StatusBadRequest)
		w.Write([]byte(err.Error()))
	}
	newBook.Id = uuid.NewString()
	b.library[newBook.Id] = newBook
	b.Unlock()
	w.Header().Add("content-type", "application/json")
	w.WriteHeader(http.StatusOK)
	allBooks := b.getAllBooks()
	jsonBytes, _ := json.Marshal(allBooks)
	w.Write(jsonBytes)

}

func newBookHandler() *bookHandlers {
	defaultBookIdFirst := uuid.NewString()
	defaultBookIdSecond := uuid.NewString()

	return &bookHandlers{
		library: map[string]Book{
			defaultBookIdFirst: {
				Id:     defaultBookIdFirst,
				Name:   "Foo Book",
				Author: "Mr. Foo",
				Pages:  33,
				Price:  10.2,
			},
			defaultBookIdSecond: {
				Id:     defaultBookIdSecond,
				Name:   "qweqwe",
				Author: "asdasd",
				Pages:  123,
				Price:  987,
			},
		},
	}
}

type Book struct {
	Id     string
	Name   string
	Author string
	Pages  int
	Price  float64
}
