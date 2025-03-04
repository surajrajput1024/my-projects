const parser = require('rss-parser');
const Feed = require('../models/feed');

const rssParser = new parser();

const fetchFeeds = async (feedUrl) => {
  try {
    const feedData = await rssParser.parseURL(feedUrl);
    const feed = new Feed({
      url: feedUrl,
      title: feedData.title,
      description: feedData.description,
      lastFetched: new Date(),
      articles: feedData.items.map((item) => ({
        title: item.title,
        link: item.link,
        summary: item.contentSnippet,
        pubDate: new Date(item.pubDate),
      })),
    });
    return feed;
  } catch (error) {
    console.error('Error fetching feed:', error);
    throw error;
  }
};

module.exports = { fetchFeeds };
