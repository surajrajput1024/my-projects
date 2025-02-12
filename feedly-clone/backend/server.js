const express = require('express');
const mongoose = require('mongoose');
const bodyParser = require('body-parser');
const cron = require('node-cron');
const dotenv = require('dotenv');
const Feed = require('./models/feed');
const { fetchFeeds } = require('./utils/fetchFeeds');

dotenv.config();
const app = express();
app.use(bodyParser.json());

const PORT = process.env.PORT || 3000;

// MongoDB Connection
mongoose.connect(process.env.MONGO_URI || 'mongodb://localhost:27017/feedly', {
  useNewUrlParser: true,
  useUnifiedTopology: true,
});

// Add feed route
app.post('/add-feed', async (req, res) => {
  try {
    const { url } = req.body;
    const newFeed = await fetchFeeds(url);
    await newFeed.save();
    res.status(200).json({ message: 'Feed added successfully!' });
  } catch (error) {
    res.status(500).json({ error: 'Failed to add feed' });
  }
});

// Get all feeds
app.get('/feeds', async (req, res) => {
  try {
    const feeds = await Feed.find();
    res.status(200).json(feeds);
  } catch (error) {
    res.status(500).json({ error: 'Failed to retrieve feeds' });
  }
});

// Health Check
app.get('/', (req, res) => {
  res.status(200).json({ message: 'Server is up and running!' });
});


// Schedule cron job to update feeds every hour
cron.schedule('0 * * * *', async () => {
  await fetchFeeds();
  console.log('Feeds updated');
});

app.listen(PORT, () => {
  console.log(`Server running on http://localhost:${PORT}`);
});

