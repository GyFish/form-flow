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

  async getFlowMeta(uuid: string): Promise<FlowMeta> {
    console.log('getFlowMeta uuid = ', uuid)

    let meta = await this.flowMetaModel
      .findOne({ uuid })
      .lean()
      .exec()

    return meta
  }

  saveFlowMeta(flowMeta: FlowMeta) {
    console.log('save flow meta...')

    let meta = {
      uuid: flowMeta.uuid,
      edges: flowMeta.graph.edges,
      nodes: flowMeta.graph.nodes,
    }

    this.flowMetaModel.create(meta)
  }
}
