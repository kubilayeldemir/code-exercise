using System;
using System.ComponentModel.DataAnnotations;

namespace EF_SQLite_exercise
{
    public class Post
    {
        [Key]
        public int Id { get; set; }
        public DateTime ScreenshotDate { get; set; }
        public DateTime CreatedAt { get; set; }
        public string UserId { get; set; }
        public string ImageUrl { get; set; }
        public string Title { get; set; }
        public string TestField { get; set; }
    }
}