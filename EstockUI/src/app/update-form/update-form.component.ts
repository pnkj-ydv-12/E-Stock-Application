import { Component, Inject } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';
import { Company } from '../model/company';

@Component({
  selector: 'app-update-form',
  templateUrl: './update-form.component.html',
  styleUrls: ['./update-form.component.css']
})
export class UpdateFormComponent {
  updateForm: FormGroup;

  constructor(
    private formBuilder: FormBuilder,
    public dialogRef: MatDialogRef<UpdateFormComponent>,
    @Inject(MAT_DIALOG_DATA) public data: Company
  ) {
    this.updateForm = this.formBuilder.group({
      companyName: [data.companyName, Validators.required],
      companyCEO: [data.companyCEO, Validators.required],
      companyTurnover: [data.companyTurnover, Validators.required],
      companyWebsite: [data.companyWebsite, Validators.required],
      stockExchange: [data.stockExchange, Validators.required],
    });
  }

  onSubmit(): void {
    if (this.updateForm.valid) {
      this.dialogRef.close(this.updateForm.value);
    }
  }

  onCancel(): void {
    this.dialogRef.close();
  }
}
