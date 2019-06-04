import { Controller, Get, Post, Body, Param, Query } from '@nestjs/common'
import { FlowService } from './flow.service'
import { FlowMeta } from './vo/FlowMeta'
import { AppResponse } from 'src/util/AppResponse'

@Controller('/service/flow')
export class FlowController {
  //
  constructor(private readonly flowService: FlowService) {}

  @Get('/')
  index(): string {
    return this.flowService.index()
  }

  @Get('/findAll')
  async findAll(): Promise<FlowMeta[]> {
    return await this.flowService.findAll()
  }

  @Get('/getFlowMeta')
  async getFlowMeta(@Query('uuid') uuid: string) {
    return AppResponse.ok(await this.flowService.getFlowMeta(uuid))
  }

  @Post('/saveFlowMeta')
  async saveFlowMeta(@Body() flowMeta: FlowMeta) {
    console.log('>> saveFlowMeta, flowMeta = ', flowMeta)
    await this.flowService.saveFlowMeta(flowMeta)
    return AppResponse.ok('save flow meta ok!')
  }
}
