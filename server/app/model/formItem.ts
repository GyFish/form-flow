import { Application } from "egg"

export default (app: Application) => {
  const { STRING, INTEGER } = app.Sequelize

  const FormItem = app.model.define(
    // model name
    "formItem",
    // 字段
    {
      id: { type: INTEGER, primaryKey: true, autoIncrement: true },
      formId: INTEGER(32), // 关联表单id
      prop: STRING(255), // 字段key
      type: STRING(255), // 类型
      label: STRING(255), // 字段名称
      placeholder: STRING(255), // 占位值
      icon: STRING(255), // 图标
      inputType: STRING(255) // 输入框类型
    },
    {
      tableName: "form",
      underscored: true,
      timestamps: true
    }
  )

  return FormItem
}
