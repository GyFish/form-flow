import { Injectable, Inject } from '@nestjs/common'
import { Model } from 'mongoose'
import { Form } from './domain/Form'

@Injectable()
export class FormService {
  constructor(
    @Inject('FormModelToken')
    private readonly formModel: Model<Form>,
  ) {}

  index(): string {
    return 'hello, i am form-service'
  }

  async findAll(): Promise<Form[]> {
    console.log('form service find all...')
    return await this.formModel.find().exec()
  }
}
