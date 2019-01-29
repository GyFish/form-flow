
> 参考 egg 官方文档

安装
```
// 依赖
npm install --save egg-sequelize mysql2
// ts 类型
npm install --save @types/sequelize
```

插件，config/plugin.ts
```
import { EggPlugin } from 'egg';

const plugin: EggPlugin = {
  sequelize: {
  sequelize: {
    enable: true,
    package: 'egg-sequelize'
  }
};

export default plugin;
```

配置，config/config.local.ts
```
import { EggAppConfig, PowerPartial } from 'egg';

export default () => {
  const config: PowerPartial<EggAppConfig> = {};

  config.sequelize = {
    dialect: 'mysql',
    host: '127.0.0.1',
    username: "root",
    password: '123456',
    port: 3306,
    database: 'csc',
  };
  
  return config;
};
```

实体类，app/model/user.ts
```
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
```

增删改查
```
import { Controller } from 'egg';

export default class HomeController extends Controller {

  public async index() {
    
    const { ctx } = this;
    ctx.body = await ctx.model.User.findAll();
    
  }
}
```