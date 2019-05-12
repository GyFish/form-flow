import { Module } from '@nestjs/common';
import { FormController } from './form.controller';
import { FormService } from './form.service';
import { MongoModule } from 'src/mongo/mongo.module';
import { FormModel } from './dao/form.model';

@Module({
  imports: [MongoModule],
  controllers: [FormController],
  providers: [FormService, ...FormModel]
})
export class FormModule {}
