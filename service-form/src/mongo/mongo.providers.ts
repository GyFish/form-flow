import * as mongoose from 'mongoose'

export const MongoProviders = [
  {
    provide: 'DbConnectionToken',
    useFactory: async (): Promise<typeof mongoose> =>
      await mongoose.connect(
        'mongodb://form_flow:form_flow@formflow.gyfish.com:27017/form_flow',
        { useNewUrlParser: true },
      ),
  },
]
