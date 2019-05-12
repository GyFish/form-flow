import * as mongoose from 'mongoose'

export const FormSchema = new mongoose.Schema({
  name: String,
  age: Number,
  breed: String,
})
