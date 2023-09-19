import { Week } from "../enums/week.enum";

export default interface NewHabit {
    name: string;
    weekDays: Week[];
}