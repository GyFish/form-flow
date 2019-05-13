import { Connection } from 'mongoose'
import { FormMetaSchema } from '../schema/FormMetaSchema'

export const FormMetaModel = [
  {
    provide: 'FormMetaModelToken',
    useFactory: (connection: Connection) => connection.model('Form', FormMetaSchema),
    inject: ['DbConnectionToken'],
  },
]
