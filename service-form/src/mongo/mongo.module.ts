import { Module } from '@nestjs/common'
import { MongoProviders } from './mongo.providers'

@Module({
  providers: [...MongoProviders],
  exports: [...MongoProviders],
})
export class MongoModule {}
