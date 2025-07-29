const express = require('express');
const cors = require('cors');
const mongoose = require('mongoose');
const jwt = require('jsonwebtoken');

const app = express();
const PORT = process.env.PORT || 5000;
const JWT_SECRET = 'majestic_secret_key';

// MongoDB connection
mongoose.connect('mongodb://localhost:27017/majestic_grid', {
  useNewUrlParser: true,
  useUnifiedTopology: true
}).then(() => console.log('✅ Connected to MongoDB'))
  .catch(err => console.error('❌ MongoDB connection failed:', err));

// Schemas
const userSchema = new mongoose.Schema({
  email: String,
  password: String
});

const brandSchema = new mongoose.Schema({
  name: String
});

const User = mongoose.model('User', userSchema);
const Brand = mongoose.model('Brand', brandSchema);

// Middleware
app.use(cors());
app.use(express.json());

// Login Route with validation
app.post('/api/login', async (req, res) => {
  const { email, password } = req.body;

  const emailRegex = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;
  const passwordRegex = /^[a-zA-Z0-9]{6,}$/;

  if (!emailRegex.test(email) || !passwordRegex.test(password)) {
    return res.status(400).json({ message: 'Invalid email or password format' });
  }

  try {
    const user = await User.findOne({ email, password });

    if (!user) {
      return res.status(401).json({ message: 'Invalid credentials' });
    }

    const token = jwt.sign({ id: user._id, email: user.email }, JWT_SECRET, { expiresIn: '1h' });
    return res.json({ token });
  } catch (error) {
    return res.status(500).json({ message: 'Server error' });
  }
});

// Get only up to 5 brands
app.get('/api/brands', async (req, res) => {
  const brands = await Brand.find().limit(5);
  res.json(brands);
});

// Create a new brand with validation, limit, and duplicate check
app.post('/api/brands', async (req, res) => {
  const { name } = req.body;

  const brandNameRegex = /^[a-zA-Z0-9\s]{2,30}$/;

  if (!brandNameRegex.test(name)) {
    return res.status(400).json({ message: 'Invalid brand name format' });
  }

  const brandCount = await Brand.countDocuments();
  if (brandCount >= 5) {
    return res.status(400).json({ message: 'Brand limit (5) reached' });
  }

  const existing = await Brand.findOne({ name: { $regex: `^${name}$`, $options: 'i' } });
  if (existing) {
    return res.status(400).json({ message: 'Brand already exists' });
  }

  const newBrand = new Brand({ name });
  await newBrand.save();

  res.status(201).json(newBrand);
});

// Update a brand
app.put('/api/brands/:id', async (req, res) => {
  const { name } = req.body;
  const { id } = req.params;

  try {
    const updated = await Brand.findByIdAndUpdate(id, { name }, { new: true });
    res.json(updated);
  } catch (err) {
    res.status(500).json({ message: 'Update failed' });
  }
});

// Delete a brand
app.delete('/api/brands/:id', async (req, res) => {
  const { id } = req.params;

  try {
    await Brand.findByIdAndDelete(id);
    res.json({ message: 'Deleted successfully' });
  } catch (err) {
    res.status(500).json({ message: 'Delete failed' });
  }
});

// Start server
app.listen(PORT, () => {
  console.log(`🚀 Server running on http://localhost:${PORT}`);
});