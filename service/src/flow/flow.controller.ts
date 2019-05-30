import { Controller, Get, Post, Body, Param, Query } from '@nestjs/common'
import { FlowService } from './flow.service'
import { FormMeta } from './vo/FormMeta'
import { AppResponse } from 'src/util/AppResponse'

@Controller('/service/flow')
export class FlowController {
  //
  constructor(private readonly formService: FlowService) {}

  @Get()
  index(): string {
    return this.formService.index()
  }

  @Get('findAll')
  async findAll(): Promise<FormMeta[]> {
    return await this.formService.findAll()
  }

  @Get('getFormMeta')
  async getFormMeta(@Query('uuid') uuid: string) {
    return AppResponse.ok(await this.formService.getFormMeta(uuid))
  }

  @Post('saveFormMeta')
  async saveFormMeta(@Body() formMeta: FormMeta) {
    console.log('>> saveFormMeta, formMeta = ', formMeta)
    await this.formService.saveFormMeta(formMeta)
    return AppResponse.ok('hello')
  }
}
