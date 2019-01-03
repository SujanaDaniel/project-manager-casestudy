import { Pipe, PipeTransform } from '@angular/core';

@Pipe({
  name: 'searchFilter',
  pure: false
})
export class SearchFilterPipe implements PipeTransform {

  transform(items: any, filter: any, screen: any): any {
    if (!items || !filter || !screen) {
      return items;
    }
    return items.filter(item => this.applyFilter(item, filter, screen));
  }

  applyFilter(taskItem: any, filterItem: any, screen: any): boolean {

    if (typeof filterItem === 'string') {
      if (screen === 'updateUser' && null !== filterItem && undefined !== filterItem && (taskItem['firstName'].toLowerCase().indexOf(filterItem.toLowerCase()) === -1 && taskItem['lastName'].toLowerCase().indexOf(filterItem.toLowerCase()) === -1 && taskItem['employeeId'].toString().indexOf(filterItem) === -1)) {
        return false;
      } else if (screen === 'updateProject' && null !== filterItem && undefined !== filterItem && (taskItem['project'].toLowerCase().indexOf(filterItem.toLowerCase()) === -1 && taskItem['startDate'].toLowerCase().indexOf(filterItem.toLowerCase()) === -1 && taskItem['endDate'].toLowerCase().indexOf(filterItem.toLowerCase()) === -1 && taskItem['priority'].toString().indexOf(filterItem) === -1
        && taskItem['completedTaskCount'].toString().indexOf(filterItem) === -1 && taskItem['totalTaskCount'].toString().indexOf(filterItem) === -1)) {
        return false;
      } else if (screen === 'parentTask' && null !== filterItem && undefined !== filterItem && taskItem['parentTask'].toLowerCase().indexOf(filterItem.toLowerCase()) === -1) {
        return false;
      }  else if (screen === 'task' && null !== filterItem && undefined !== filterItem && taskItem['project'].toLowerCase().indexOf(filterItem.toLowerCase()) === -1) {
        return false;
      }
    }
    return true;
  }
}