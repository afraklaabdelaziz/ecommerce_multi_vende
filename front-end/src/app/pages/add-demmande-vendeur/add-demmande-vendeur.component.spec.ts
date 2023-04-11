import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AddDemmandeVendeurComponent } from './add-demmande-vendeur.component';

describe('AddDemmandeVendeurComponent', () => {
  let component: AddDemmandeVendeurComponent;
  let fixture: ComponentFixture<AddDemmandeVendeurComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ AddDemmandeVendeurComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(AddDemmandeVendeurComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
