import { Module } from '@nestjs/common';
import { FlowController } from './flow.controller';
import { FlowService } from './flow.service';
import { MongoModule } from 'src/mongo/mongo.module';
import { FlowMetaModel } from './model/FlowMetaModel';

@Module({
  imports: [MongoModule],
  controllers: [FlowController],
  providers: [FlowService, ...FlowMetaModel]
})
export class FlowModule {}
