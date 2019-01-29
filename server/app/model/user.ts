import { Application } from 'egg';

export default (app: Application) => {
  
  const { STRING, INTEGER, JSON } = app.Sequelize;

  const User = app.model.define('user', {
    id: { type: INTEGER, primaryKey: true, autoIncrement: true },
    name: STRING(30),
    age: INTEGER,
    info: JSON
  }, {
    tableName: 'user',
    underscored: true,
    timestamps: false,
  })
  
  return User
}