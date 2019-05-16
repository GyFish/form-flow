import { Injectable, Inject } from '@nestjs/common'
import { Model } from 'mongoose'
import { FormMeta } from './vo/FormMeta'

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

  async getFormMeta(uuid: string): Promise<FormMeta> {
    console.log('getFormMeta uuid = ', uuid)

    let meta = await this.formMetaModel
      .findOne({ uuid })
      .lean()
      .exec()

    if (meta) {
      return meta.items
    }
  }

  saveFormMeta(formMeta: FormMeta) {
    this.formMetaModel.create(formMeta)
  }
}
