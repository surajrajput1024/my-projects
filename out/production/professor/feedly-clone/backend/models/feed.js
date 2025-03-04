const mongoose = require('mongoose');

const feedSchema = new mongoose.Schema({
  url: String,
  title: String,
  description: String,
  lastFetched: Date,
  articles: [
    {
      title: String,
      link: String,
      summary: String,
      pubDate: Date,
    },
  ],
});

const Feed = mongoose.model('Feed', feedSchema);
module.exports = Feed;
