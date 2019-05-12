import { Controller, Get } from '@nestjs/common'
import { FormService } from './form.service'
import { Form } from './domain/Form'
import { async } from 'rxjs/internal/scheduler/async'

@Controller('form')
export class FormController {
  //
  // 注入 service
  constructor(private readonly formService: FormService) {}

  @Get()
  index(): string {
    return this.formService.index()
  }

  @Get('findAll')
  async findAll(): Promise<Form[]> {
    return await this.formService.findAll()
  }
}
