using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;
using Microsoft.AspNetCore.Mvc;
using Microsoft.EntityFrameworkCore;
using Microsoft.Extensions.Logging;

namespace EF_SQLite_exercise.Controllers
{
    [ApiController]
    [Route("[controller]")]
    public class WeatherForecastController : ControllerBase
    {
        private static readonly string[] Summaries = new[]
        {
            "Freezing", "Bracing", "Chilly", "Cool", "Mild", "Warm", "Balmy", "Hot", "Sweltering", "Scorching"
        };

        private readonly PostContext _postContext;

        private readonly ILogger<WeatherForecastController> _logger;

        public WeatherForecastController(ILogger<WeatherForecastController> logger, PostContext postContext)
        {
            _logger = logger;
            _postContext = postContext;
        }

        [HttpGet]
        public IEnumerable<WeatherForecast> Get()
        {
            var rng = new Random();
            return Enumerable.Range(1, 5).Select(index => new WeatherForecast
                {
                    Date = DateTime.Now.AddDays(index),
                    TemperatureC = rng.Next(-20, 55),
                    Summary = Summaries[rng.Next(Summaries.Length)]
                })
                .ToArray();
        }

        [HttpGet("posts")]
        public async Task<IActionResult> GetPosts()
        {
            var posts = _postContext.Posts.AsQueryable();
            return Ok(posts.ToList());
        }

        [HttpGet("post")]
        public async Task<IActionResult> GetPost(int id)
        {
            var post = await _postContext.FindAsync<Post>(id);
            return Ok(post);
        }

        [HttpPost("posts")]
        public async Task<IActionResult> SavePost(Post post)
        {
            await _postContext.Posts.AddAsync(post);
            await _postContext.SaveChangesAsync();
            return Ok(post);
        }

        [HttpPost("updatePost")]
        public async Task<IActionResult> UpdatePost(Post post)
        {
            _postContext.Posts.Attach(post);
            foreach (var property in _postContext.Entry(post).Properties)
            {
                if (property.OriginalValue == null)
                {
                    continue;
                }

                var isDateTime = property.OriginalValue.GetType()?.FullName == "System.DateTime";

                if (!isDateTime && property.OriginalValue != null && !property.Metadata.IsPrimaryKey())
                {
                    property.IsModified = true;
                }
                else if (isDateTime)
                {
                    var dateObject = (DateTime) property.OriginalValue;
                    if (dateObject != DateTime.MinValue)
                    {
                        property.IsModified = true;
                    }
                }
            }

            await _postContext.SaveChangesAsync();
            return Ok(post);
        }

        [HttpPost("postsLike")]
        public async Task<IActionResult> SavePost2(Post post)
        {
            await _postContext.AddAsync(new Like());
            await _postContext.SaveChangesAsync();
            return Ok(post);
        }

        [HttpPost("postsDislike")]
        public async Task<IActionResult> SavePost2(Dislike dislike)
        {
            await _postContext.AddAsync(new Dislike());
            await _postContext.SaveChangesAsync();
            return Ok(dislike);
        }
    }
}