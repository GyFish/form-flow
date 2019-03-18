import { EggPlugin } from "egg"

const plugin: EggPlugin = {
  
  sequelize: {
    enable: true,
    package: "egg-sequelize"
  },

  cors: {
    enable: true,
    package: "egg-cors"
  }
  
}

export default plugin
