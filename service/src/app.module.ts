import { Module } from '@nestjs/common';
import { FormModule } from './form/form.module';
import { MongoModule } from './mongo/mongo.module';
import { FlowModule } from './flow/flow.module';

@Module({
  imports: [MongoModule, FormModule, FlowModule],
})
export class AppModule {}
