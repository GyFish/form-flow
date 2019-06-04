import * as mongoose from 'mongoose'

export const FlowMetaSchema = new mongoose.Schema(
  {
    uuid: String,
    edges: [Object],
    nodes: [Object]
  },
  {
    collection: 'flow_meta',
    timestamps: true,
  },
)
