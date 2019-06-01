import * as mongoose from 'mongoose'

export const FlowMetaSchema = new mongoose.Schema(
  {
    uuid: String,
    form: {
      title: String,
    },
    items: [
      {
        itemType: String,
        prop: String,
        label: String,
      },
    ],
  },
  {
    collection: 'flow_meta',
    timestamps: true,
  },
)
