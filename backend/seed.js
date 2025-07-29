// server/seed.js
const mongoose = require('mongoose');

// Define the User model
const userSchema = new mongoose.Schema({
  email: String,
  password: String
});
const User = mongoose.model('User', userSchema);

// Create only ONE manager user
async function seed() {
  await mongoose.connect('mongodb://localhost:27017/majestic_grid');

  const email = 'manager@majestic.com';
  const password = '123456';

  const existingUser = await User.findOne({ email });
  if (existingUser) {
    console.log('ℹ️ User already exists.');
  } else {
    await new User({ email, password }).save();
    console.log('✅ Manager user created successfully.');
  }

  process.exit();
}

seed().catch(err => {
  console.error('❌ Seeding failed:', err);
  process.exit(1);
});