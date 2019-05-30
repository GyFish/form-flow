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
    collection: 'form_meta',
    timestamps: true,
  },
)