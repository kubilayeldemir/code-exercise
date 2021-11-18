package main

import (
	"encoding/json"
	"fmt"
	"net/http"
)

func main() {
	bookHandlers := newBookHandler()
	http.HandleFunc("/books",bookHandlers.get)
	
	port := ":8080"
	fmt.Println("Started listening on port",port)
	err := http.ListenAndServe(port,nil)
	if err != nil {
		panic("amanın haa")		
	}
}

type  bookHandlers struct {
	library map[string]Book
}

func (b *bookHandlers) get(w http.ResponseWriter, r *http.Request) {
	var books []Book
	for _,book := range b.library {
		books = append(books, book)
	}
	jsonBytes, err := json.Marshal(books)
	if err != nil {
		//TODO		
	}	
	
	w.Write(jsonBytes)
}

func newBookHandler() *bookHandlers {
	return  &bookHandlers{
		library: map[string]Book{
			"id1":{
				Name:   "Foo Book",
				Author: "Mr. Foo",
				Pages:  33,
				Price:  10.2,
			},
			"id2": {
				Name:   "qweqwe",
				Author: "asdasd",
				Pages:  123,
				Price:  987,
			},
		},
	}
}

type Book struct {
	Name string
	Author string
	Pages int
	Price float64
}

