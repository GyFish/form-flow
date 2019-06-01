import { Injectable, Inject } from '@nestjs/common'
import { Model } from 'mongoose'
import { FlowMeta } from './vo/FlowMeta'

@Injectable()
export class FlowService {
  //
  constructor(
    @Inject('FlowMetaModelToken')
    private readonly flowMetaModel: Model<FlowMeta>,
  ) {}

  index(): string {
    return 'hello, i am flow-service'
  }

  async findAll(): Promise<FlowMeta[]> {
    console.log('form service find all...')
    return await this.flowMetaModel.find().exec()
  }

  async getFormMeta(uuid: string): Promise<FlowMeta> {
    console.log('getFormMeta uuid = ', uuid)

    let meta = await this.flowMetaModel
      .findOne({ uuid })
      .lean()
      .exec()

    if (meta) {
      return meta.items
    }
  }

  saveFormMeta(flowMeta: FlowMeta) {

    console.log('save form meta...')
    
    // this.formMetaModel.create(formMeta)
  }
}
