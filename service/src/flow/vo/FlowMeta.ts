import { Document } from 'mongoose'

export interface FlowMeta extends Document {
  uuid: string
  graphData: string
}
