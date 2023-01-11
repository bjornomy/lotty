

/** @type {import('./$types').Actions} */
export const actions = {
  submit: async ({fetch, request}: any) => {
    const data = await request.formData();
    const lottery: any = {}
    data.forEach((value: any, key:any ) => lottery[key] = value)
    console.log(lottery)
  }
}