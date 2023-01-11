/* tslint:disable */
/* eslint-disable */
// Generated using typescript-generator version 3.1.1185 on 2023-01-10 19:05:04.

export interface Drawing extends TsidAware {
    id: string;
    prices: Price[];
    drawnAt: Date;
}

export interface Lottery extends TsidAware {
    id: string;
    name: string;
    participants: Participant[];
    drawings: Drawing[];
}

export interface Participant extends TsidAware {
    id: string;
    name: string;
    wins: number;
    participations: number;
}

export interface Price extends TsidAware {
    id: string;
    name: string;
    description: string;
    winner: string;
}

export interface Schedule extends TsidAware {
    id: string;
    scheduleFrequency: any;
    target: string;
    description: string;
}

export interface TsidAware {
}

export interface UserInfo {
    name: string;
    email: string;
    identifier: string;
    pictureUrl: string;
    provider: string;
}

export interface CreateLottery {
    name: string;
    participationFee: number;
    schedule: Schedule;
}

export interface CreateSchedule {
    scheduleFrequency: any;
    target: string;
}

export interface MonthTarget extends ScheduleTarget {
    dayOfMonth: number;
}

export interface ScheduleTarget {
}

export interface WeekTarget extends ScheduleTarget {
    dayOfWeek: any;
}
