import { Injectable, Inject } from '@nestjs/common'
import { Model } from 'mongoose'
import { FormMeta } from './domain/FormMeta'

@Injectable()
export class FormService {
  //
  constructor(
    @Inject('FormMetaModelToken')
    private readonly formMetaModel: Model<FormMeta>,
  ) {}

  index(): string {
    return 'hello, i am form-service'
  }

  async findAll(): Promise<FormMeta[]> {
    console.log('form service find all...')
    return await this.formMetaModel.find().exec()
  }

  saveFormMeta(formMeta: FormMeta) {
    this.formMetaModel.create(formMeta)
  }
}
