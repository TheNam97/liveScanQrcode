import { ComponentFixture, TestBed } from '@angular/core/testing';

import { CheckGroupIdComponent } from './check-group-id.component';

describe('CheckGroupIdComponent', () => {
  let component: CheckGroupIdComponent;
  let fixture: ComponentFixture<CheckGroupIdComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ CheckGroupIdComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(CheckGroupIdComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
