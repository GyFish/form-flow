import { Application } from "egg"

export default (app: Application) => {
  const { STRING, INTEGER } = app.Sequelize

  const Form = app.model.define(
    // model name
    "form",
    // 字段
    {
      id: { type: INTEGER, primaryKey: true, autoIncrement: true },
      title: STRING(255)
    },
    {
      tableName: "form",
      underscored: true,
      timestamps: true
    }
  )

  return Form
}
