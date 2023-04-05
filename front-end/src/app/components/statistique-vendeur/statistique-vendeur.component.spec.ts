import { ComponentFixture, TestBed } from '@angular/core/testing';

import { StatistiqueVendeurComponent } from './statistique-vendeur.component';

describe('StatistiqueVendeurComponent', () => {
  let component: StatistiqueVendeurComponent;
  let fixture: ComponentFixture<StatistiqueVendeurComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ StatistiqueVendeurComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(StatistiqueVendeurComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
