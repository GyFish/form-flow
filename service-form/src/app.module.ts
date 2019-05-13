import { Module } from '@nestjs/common';
import { FormModule } from './form/form.module';
import { MongoModule } from './mongo/mongo.module';

@Module({
  imports: [MongoModule, FormModule],
})
export class AppModule {}
