import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ListDemmandeVendeurComponent } from './list-demmande-vendeur.component';

describe('ListDemmandeVendeurComponent', () => {
  let component: ListDemmandeVendeurComponent;
  let fixture: ComponentFixture<ListDemmandeVendeurComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ListDemmandeVendeurComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ListDemmandeVendeurComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
