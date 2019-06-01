import { Connection } from 'mongoose'
import { FlowMetaSchema } from '../schema/FlowMetaSchema'

export const FlowMetaModel = [
  {
    provide: 'FlowMetaModelToken',
    useFactory: (connection: Connection) => connection.model('Flow', FlowMetaSchema),
    inject: ['DbConnectionToken'],
  },
]
