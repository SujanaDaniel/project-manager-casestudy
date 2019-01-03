
import { Pipe, PipeTransform } from "@angular/core";

@Pipe({
    name: 'sortPipe'
})

export class SortPipe implements PipeTransform {

    transform(items: any, key: string): any {

        if (key === undefined || key == '' || !items) {
            return items;
        }
        var array = key.split("-");
        var keyValue = array[0];
        var order = array[1];
        var val = 1;

        items.sort((a: any, b: any) => {

            if (a[keyValue] < b[keyValue]) {
                return (order === "asc") ? -1 * val : 1 * val;
            } else if (a[keyValue] > b[keyValue]) {
                return (order === "asc") ? 1 * val : -1 * val;
            } else {
                return 0;
            }
        });
        return items;
    }
}
