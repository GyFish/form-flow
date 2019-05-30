import { Connection } from 'mongoose'
import { FlowMetaSchema } from '../schema/FormMetaSchema'

export const FlowMetaModel = [
  {
    provide: 'FormMetaModelToken',
    useFactory: (connection: Connection) => connection.model('Form', FlowMetaSchema),
    inject: ['DbConnectionToken'],
  },
]
