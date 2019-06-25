export class Item {
  idx: number = 0
  uuid: string = ''
  prop: string = ''
  value: string = ''
  itemType: string = ''
  label: string = ''
  inputType: string = ''
  placeholder: string = ''
  icon: string = ''
  prefixIcon: string = ''
}

export class BaseItem {
  static form = {
    inline: false
  }
  static items = [
    {
      itemType: 'el-input',
      prop: 'input',
      label: '文本框',
      icon: 'el-icon-edit',
      type: 'text'
    },
    {
      itemType: 'el-input',
      prop: 'input',
      label: '多行文本',
      icon: 'el-icon-tickets',
      type: 'textarea'
    },
    {
      itemType: 'el-select',
      prop: 'input',
      label: '下拉框',
      icon: 'el-icon-arrow-down',
      options: [
        {
          label: '西瓜',
          value: 'watermelon'
        },
        {
          label: '可乐',
          value: 'cola'
        }
      ]
    },
    {
      itemType: 'el-date-picker',
      prop: 'input',
      label: '日期选择器',
      icon: 'el-icon-time'
    }
  ]
}

export class FormConfigData {
  title: string = ''
}
