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
      prop: 'input',
      type: 'el-input',
      label: '文本框',
      icon: 'el-icon-edit'
    },
    {
      type: 'el-input',
      prop: 'input',
      label: '多行文本',
      icon: 'el-icon-tickets'
    },
    {
      type: 'el-select',
      prop: 'input',
      label: '下拉框',
      icon: 'el-icon-arrow-down'
    },
    {
      type: 'el-date-picker',
      prop: 'input',
      label: '日期选择器',
      icon: 'el-icon-time'
    }
  ]
}

export class FormConfigData {
  title: string = ''
}
