using Microsoft.EntityFrameworkCore;

namespace EF_SQLite_exercise
{
    public class PostContext : DbContext
    {
        public PostContext(DbContextOptions<PostContext> options) : base(options)
        {
            
        }

        public DbSet<Post> Posts { get; set; }
        public DbSet<Like> Likes { get; set; }

    }
}