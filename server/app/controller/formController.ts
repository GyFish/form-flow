import { Controller } from "egg"

export default class FormController extends Controller {
  public async saveForm() {
    this.logger.info("saving form")
    const { ctx } = this
    const form = ctx.request.body
    await ctx.service.formService.saveForm(form)
    ctx.body = "save form ok!"
  }
}
