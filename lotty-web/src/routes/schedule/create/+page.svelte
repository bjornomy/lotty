<script lang="ts">
  import Card from '$lib/components/Card.svelte'
  import Input from '$lib/components/form/Input.svelte'
</script>

<Card title="Create Schedule">
  <Input type="text" id="txt_schedule_frequency" placeholder="Frequency"/>
  <Input type="text" id="txt_schedule_target" placeholder="Target"/>
</Card>