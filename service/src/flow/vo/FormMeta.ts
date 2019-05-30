import { Document } from 'mongoose'

export interface FormMeta extends Document {
  uuid: string
  title: string
}
