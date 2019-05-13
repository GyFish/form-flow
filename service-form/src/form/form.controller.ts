import { Controller, Get, Post, Body } from '@nestjs/common'
import { FormService } from './form.service'
import { FormMeta } from './domain/FormMeta'
import { AppResponse } from 'src/util/AppResponse'

@Controller('form')
export class FormController {
  //
  constructor(private readonly formService: FormService) {}

  @Get()
  index(): string {
    return this.formService.index()
  }

  @Get('findAll')
  async findAll(): Promise<FormMeta[]> {
    return await this.formService.findAll()
  }

  /**
   * 保存表单定义
   */
  @Post('saveFormMeta')
  async saveFormMeta(@Body() formMeta: FormMeta) {
    console.log('>> saveFormMeta, formMeta = ', formMeta)
    await this.formService.saveFormMeta(formMeta)
    return AppResponse.ok('hello')
  }
}
