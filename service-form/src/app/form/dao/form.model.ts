import { Connection } from 'mongoose'
import { FormSchema } from './form.schema'

export const FormModel = [
  {
    provide: 'FormModelToken',
    useFactory: (connection: Connection) => connection.model('Form', FormSchema),
    inject: ['DbConnectionToken'],
  },
]
