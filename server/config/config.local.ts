import { EggAppConfig, PowerPartial } from 'egg';

export default () => {
  const config: PowerPartial<EggAppConfig> = {};

  config.sequelize = {
    dialect: 'mysql',
    host: '127.0.0.1',
    username: "root",
    password: '123456',
    port: 3306,
    database: 'test',
  }

  config.security = {
    domainWhiteList: [ 'http://localhost:8080' ],
    csrf: {
      enable: false
    }
  }

  
  return config
};
