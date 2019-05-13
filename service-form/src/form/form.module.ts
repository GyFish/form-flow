import { Module } from '@nestjs/common';
import { FormController } from './form.controller';
import { FormService } from './form.service';
import { MongoModule } from 'src/mongo/mongo.module';
import { FormMetaModel } from './model/FormMetaModel';

@Module({
  imports: [MongoModule],
  controllers: [FormController],
  providers: [FormService, ...FormMetaModel]
})
export class FormModule {}
