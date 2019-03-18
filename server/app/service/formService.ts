import { Service } from "egg"

export default class FormService extends Service {
  public async saveForm(formBody: any) {
    let { form, items } = formBody
    this.logger.info(form)
    this.logger.info(items)
    await this.ctx.model.Form.create(form)
    await this.ctx.model.FormItem.bulkCreate(items)
  }
}
